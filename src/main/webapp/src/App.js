import React, { Component} from 'react';
import Demo from './components/Login'
import { Row, Col, Layout, Popconfirm, Table, Button, Modal, Form, Input, InputNumber, DatePicker } from 'antd';
import './App.css'
import ModalContent from './components/ModalContent'

const { Header, Content } = Layout;
const axios = require('axios')

class App extends Component {

  constructor(props){
    super(props);

    this.submitFunction = this.submitFunction.bind(this)
    this.refresher = this.refresher.bind(this)
    this.onDelete = this.onDelete.bind(this)
    this.showModal = this.showModal.bind(this)
    this.handleCancel = this.handleCancel.bind(this)
    this.onFinish = this.onFinish.bind(this)
    this.onFinishFailed = this.onFinishFailed.bind(this)
    this.onChange = this.onChange.bind(this)
    this.onChangee = this.onChangee.bind(this)

    this.state = {updatemodal: {
      eventName: "" ,
      eventStartDate: "" ,
      eventEndDate: "" ,
      eventQuota: "" ,
      eventQuestion: ""
    }, 
          
          visible: false, columns: [
        {
          title: 'Name',
          dataIndex: 'eventName',
          key: 'eventName',
        },
        {
          title: 'Start Date',
          dataIndex: 'eventStartDate',
          key: 'eventStartDate',
        },
        {
          title: 'Finish Date',
          dataIndex: 'eventEndDate',
          key: 'eventEndDate',
        },
        {
          title: 'Quota',
          key: 'eventQuota',
          dataIndex: 'eventQuota',
        },
        {
          title: 'Action',
          key: 'action',
          render: (text, record) => (<>
              <Popconfirm title="Sure to update?" onConfirm={() =>{this.setState({updatemodal: {                      
                                                                          eventName: record.eventName,
                                                                          //eventStartDate: record.eventStartDate,
                                                                          //eventEndDate: record.eventEndDate,
                                                                          eventQuota: record.eventQuota,
                                                                          eventQuestion: record.eventQuestion, 
              }}); this.showModal()}}>
                <a>Update</a>
              </Popconfirm>
              <br/>
              <Popconfirm title="Sure to delete?" onConfirm={() => this.onDelete(record)}>
                <a>Delete</a>
              </Popconfirm></>
          ),
        },
      ],
      
      data: []
    }
  }

  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  onFinish = (values) => {
    let putData = {
      eventName: values.eventName ,
      eventStartDate: this.state.eventStartDate ,
      eventEndDate: this.state.eventEndDate ,
      eventQuota: values.eventQuota ,
      eventQuestion: values.eventQuestion === undefined ? "" : values.eventQuestion};
      
  let axiosConfig = {headers : {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem('token')
      }};

  let goTo = "/" + values.eventName + "";
  console.log("tried to update");
  try {
      axios.put(goTo, putData, axiosConfig).then( response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error);
        }); 
      
    } catch (error) { 
      console.error(error)
    }
    this.setState({
      visible: false,
    });
};

 onFinishFailed = errorInfo => {
    console.log('Failed:', errorInfo);
};

onChange = (value, dateString) => {
  this.setState({eventStartDate: dateString});
}

onChangee = (value, dateString) => {
  this.setState({eventEndDate: dateString});
}

  refresher = () => {
    console.log(this.state.updatemodal)
    if(localStorage.getItem("token") !== null){
      try {
        axios.get('/events', { headers: {"Authorization" : localStorage.getItem('token') }}).then( response => {
            this.setState({data: response.data});
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          });  
        
      } catch (error) { 
        console.error(error)
      }
  }else{
    console.log("olmadı")
  }};

  onDelete = (values) => {
        
    let axiosConfig = {headers : {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        }};

    let goTo = "/" + values.eventName + "";
        console.log("tried to delete");
    try {
        axios.delete(goTo, axiosConfig).then( response => {
            console.log(response)
          })
          .catch(error => {
            console.log(error);
          }); 
        
      } catch (error) { 
        console.error(error)
      }
  };

   submitFunction (values){
    console.log('Success:', values);
    try {
      axios.post('/login',{
        username: values.username ,
        password: values.password
      }).then( response => {
          let token = response.data.token;
          if(token !== undefined){
            localStorage.setItem("token", 'Bearer ' + token);
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
          }
          
        })
        .catch(error => {
          console.log(error);
        }); 
      
    } catch (error) { 
      console.error(error)
    }
  };

  render(){
    return (
      <div>
        <Layout className="layout">
          <Header></Header>
          <Content>
            <Row align="middle" className="content">
              <Col span={10} offset={6}>
              <Modal
                      title="Update Event"
                      visible={this.state.visible}
                      onCancel={this.handleCancel}
                      footer={null}
                      >
              <Form
                layout
                name="basic"
                onFinish={this.onFinish}
                onFinishFailed={this.onFinishFailed}
                >
                <Form.Item
                    label="Event Name"
                    name="eventName"
                    rules={[{ required: true, message: 'Please input Event Name!' }]}
                >
                    <Input defaultValue = {this.state.updatemodal.eventName} />
                </Form.Item>
                <Form.Item
                    label="Event Start Date"
                    name="eventStartDate"
                    rules={[{ required: true, message: 'Please input Event Start Date!' }]}
                >
                    <DatePicker
                        showTime={{ format: 'HH:mm' }}
                        format="YYYY-MM-DD HH:mm"
                        onChange={this.onChange}
                        />
                </Form.Item>
                <Form.Item
                    label="Event Finish Date"
                    name="eventEndDate"
                    rules={[{ required: true, message: 'Please input Event End Date!' }]}
                >
                    <DatePicker
                        showTime={{ format: 'HH:mm' }}
                        format="YYYY-MM-DD HH:mm"
                        onChange={this.onChangee}
                        />
                </Form.Item>
                <Form.Item
                    label="Event Quota"
                    name="eventQuota"
                    rules={[{ required: true, message: 'Please input Event Quota!' }]}
                >
                    <InputNumber defaultValue = {this.state.updatemodal.eventQuota} />
                </Form.Item>              
                <Form.Item
                    label="Event Question"
                    name="eventQuestion"
                    rules={[{ required: false }]}
                >
                    <Input defaultValue = {this.state.updatemodal.eventQuestion} />
                </Form.Item>
                <Form.Item tailLayout>
                    <Button type="primary" htmlType="submit">
                      Submit
                    </Button>
                </Form.Item>
                </Form>
                </Modal>
              { 
                localStorage.getItem("token") === null ? <Demo submitFunction={this.submitFunction} /> : <>
                  <Button onClick={this.refresher}>Refresh Event Table</Button>
                  <Table dataSource={this.state.data} columns={this.state.columns}/> 
                  <ModalContent></ModalContent>
                </>
              }
              </Col>
            </Row>
          </Content>
        </Layout>
      </div>
    );
  }
      
  
}

export default App;
