package life.league.challenge.kotlin.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_post_list.postList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostListFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()
    private val postAdapter: PostAdapter by lazy { PostAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.postList.collectLatest {
                postAdapter.posts = it
                postList.adapter = postAdapter
                postList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}