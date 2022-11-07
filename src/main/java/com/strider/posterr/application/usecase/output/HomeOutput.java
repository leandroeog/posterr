package com.strider.posterr.application.usecase.output;

import com.strider.posterr.application.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class HomeOutput {

    Page<Post> posts;

}
