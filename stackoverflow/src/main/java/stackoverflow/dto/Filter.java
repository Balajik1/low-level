package stackoverflow.dto;

import stackoverflow.model.Tag;

//client or user add mutliple filter to get questions but for now considering two
public class Filter {
    private Tag tag;
    private String authorId;

    private Filter() {
    }

    private Filter(Tag tag, String authorId) {
        this.tag = tag;
        this.authorId = authorId;
    }

    public Tag getTag() {
        return tag;
    }

    public String getAuthorId() {
        return authorId;
    }

    public static class Builder {
        private Tag tag;
        private String authorId;

        public Builder tag(Tag tag) {
            this.tag = tag;
            return this;
        }

        public Builder authorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public Filter build() {
            return new Filter(tag, authorId);
        }
    }
}
