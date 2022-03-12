package com.pedro.doggos.feature_home.data

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.pedro.doggos.feature_home.data.remote.service.BreedImagesService
import com.pedro.doggos.feature_home.domain.model.BreedImage
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

private const val IMAGE_STARTING_PAGE_INDEX = 0
private const val PAGINATION_COUNT_HEADER = "pagination-count"

class BreedImagesPagingSource (
    private val service: BreedImagesService,
    private val query: String
): RxPagingSource<Int, BreedImage>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BreedImage>> {
        val position = params.key ?: IMAGE_STARTING_PAGE_INDEX

        return service.getBreedImages(order = query, page = position)
            .subscribeOn(Schedulers.io())
            .map { response ->
                toLoadResult(
                    data = response.body().orEmpty().map { it.toBreedImage() },
                    position = position,
                    totalPages = response.headers()[PAGINATION_COUNT_HEADER]?.toInt())
            }
    }

    private fun toLoadResult(data: List<BreedImage>, position: Int, totalPages: Int?): LoadResult<Int, BreedImage> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 0) null else position - 1,
            nextKey = if (position == totalPages) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, BreedImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}