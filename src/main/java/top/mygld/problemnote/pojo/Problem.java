package top.mygld.problemnote.pojo;

public class Problem {
    private Integer id;
    private String content;
    private String answer;
    private Integer subjectId;
    private Integer tagId;
    
    // 添加关联对象，用于显示科目和标签名称
    private Subject subject;
    private Tag tag;
    
    // 添加错题标记字段
    private Boolean isMarked;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Integer getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    public Integer getTagId() {
        return tagId;
    }
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
    public Boolean getIsMarked() {
        return isMarked;
    }
    public void setIsMarked(Boolean isMarked) {
        this.isMarked = isMarked;
    }
} 