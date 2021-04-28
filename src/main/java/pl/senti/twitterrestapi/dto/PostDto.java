package pl.senti.twitterrestapi.dto;

import java.time.LocalDateTime;

public class PostDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;

    private PostDto() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public static final class Builder {
        private long id;
        private String title;
        private String content;
        private LocalDateTime created;


        public Builder id(long val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder created(LocalDateTime val) {
            created = val;
            return this;
        }

        public PostDto build() {
            PostDto postDto = new PostDto();
            postDto.id = this.id;
            postDto.title = this.title;
            postDto.content = this.content;
            postDto.created = this.created;
            return postDto;
        }
    }
}
