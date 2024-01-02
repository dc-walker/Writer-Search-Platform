import {Descriptions} from "antd";
//书籍信息展示，传入一本书的json
const labelMap = new Map([
    ['title','书籍'],
    ['briefIntroduction','简介'],
    ['literaryGenres','体裁'],
    ['publicationYear','出版日期'],
])
const BookDetail =(props) =>{
    const items = Object.entries(props.book).map(([key,value],index) => {
        if (key !== 'source' && key !== 'honors' && key !== 'authorId' && key !== 'id') {
            return {
                key: (index + 1).toString(),
                label: labelMap.get(key),
                children: value
            };
        }
        return null;
    }).filter(item => item != null)
    return (
        <Descriptions items={items} title={props.book.title} column={1} size={"middle"} />
    )
}
export default BookDetail;
