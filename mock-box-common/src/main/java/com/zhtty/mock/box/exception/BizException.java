package com.zhtty.mock.box.exception;

/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.springframework.context.i18n.LocaleContextHolder;


/**
 * @author A8142
 */
public class BizException extends RuntimeException {

    private Integer code;

    private Object data;

    public <T> BizException(ExceptionMessageEnum exceptionMessageEnum, T data) {

        this.code = exceptionMessageEnum.code;
        this.data = data;
    }

    public BizException(ExceptionMessageEnum exceptionMessageEnum) {

        this.code = exceptionMessageEnum.code;
    }

    public BizException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
