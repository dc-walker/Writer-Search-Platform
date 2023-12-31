import {Avatar, Card, Descriptions, DescriptionsProps, Flex, Image, Space} from "antd";
import "./WriterDetail.css"
import Meta from "antd/es/card/Meta";
import {useEffect, useState} from "react";
const labelMap = new Map([
  ['name','姓名'],
  ['age','年龄'],
  ['gender','性别'],
  ['birthDate' , '出生日期'],
  ['deathDate','逝世日期'],
  ['writingStyle','写作风格'],
  ['provence','所属省份'],
  ['pseudonym','别名'],
  ['representative','代表作'],
  ['briefIntroduction','简介'],
  ['honors','荣誉']
])
const WriterDetail = (props) =>{
  const {name,id} = props//作家名，id
  const [items, setItems] = useState([])
  const [img,setImg] = useState('')
  const writerURL = 'http://localhost:8090/api/writerDetail'
  useEffect(() =>{
    fetch(`${writerURL}?id=${id}`).then(resp => resp.json())
        .then(data =>{
          //获取作家信息，不显示imgUrl
          const itemTemp = Object.entries(data).map(([key,value], index) =>{
            if(key !== 'imgUrl' && key !== 'url' && key !== 'city' && key !== 'id'){
              return {
                key:(index+1).toString(),
                label:labelMap.get(key),
                children:value,
              };
            }
            return null;
          }).filter(item => item != null)
          setItems(itemTemp)
          setImg(data.imgUrl)
          console.log(data)
        })
  },[])
  return(
      <div style={{color: "#ffffff", marginTop:"2rem",marginBottom: "2rem"}}>
        <Card style={{width: "95%", backgroundColor:"rgba(255,255,255,0.6)", backdropFilter:"blur(8px)"}}>
          <Flex align={"center"}>
            <Meta
                avatar={<Image src={img} width={"150px"}></Image>}
            />
            <Descriptions title={name} items={items} column={2} size={"middle"} className={'custom-description'}/>
          </Flex>
        </Card>
      </div>
  )
}
export default WriterDetail;
