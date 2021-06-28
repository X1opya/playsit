package dev.playsit.core.utils

import dev.playsit.core.network.configurations.result.ApiResult

interface BaseOffsetModel<OUT, IN> {
    suspend operator fun invoke(offset: Int, param: IN): ApiResult<List<OUT>>
}
