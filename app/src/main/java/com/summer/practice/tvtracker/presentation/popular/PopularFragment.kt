package com.summer.practice.tvtracker.presentation.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.data.MovieRepositoryFactory
import com.summer.practice.tvtracker.data.networking.makeToast
import com.summer.practice.tvtracker.databinding.FragmentPopularBinding
import com.summer.practice.tvtracker.domain.Movie
import com.summer.practice.tvtracker.presentation.MovieAdapter
import com.summer.practice.tvtracker.presentation.details.DetailsActivity


class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MovieRepositoryFactory.createRepository().getMovies(
            "popular",
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )

        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        binding.recyclerViewPopular.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPopular.adapter = MovieAdapter(
            movies,
            object : MovieAdapter.ItemClickListener {
                override fun onItemClicked(id: Int) {
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            })
    }

    private fun onError(error: String) {
        makeToast(requireActivity(), error)
    }
}