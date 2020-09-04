import React, { Component } from 'react'
import { Form, Input, Button, DatePicker, InputNumber, Modal } from 'antd';
const axios = require('axios')

class ModalContent extends Component {

    constructor(props){
        super(props);
    
        this.showModal = this.showModal.bind(this)
        this.handleCancel = this.handleCancel.bind(this)
        this.onFinish = this.onFinish.bind(this)
        this.onFinishFailed = this.onFinishFailed.bind(this)
        this.onChange = this.onChange.bind(this)
        this.onChangee = this.onChangee.bind(this)
    
        this.state = {visible: false, eventStartDate : "", eventEndDate: ""
            
        }
    }

     layout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
    };
    tailLayout = {
        wrapperCol: { offset: 8, span: 16 },
    };

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
        //console.log('Success:', values, this.state.eventStartDate, this.state.eventEndDate);
        let postData = {
            eventName: values.eventName ,
            eventStartDate: this.state.eventStartDate ,
            eventEndDate: this.state.eventEndDate ,
            eventQuota: values.eventQuota ,
            eventQuestion: values.eventQuestion === undefined ? "" : values.eventQuestion};

        let axiosConfig = {headers : {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem('token')
            }};
        try {
            axios.post('/addEvent',postData, axiosConfig).then( response => {
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

    render(){
        return(
            <>
            <Modal
                    title="Add Event"
                    visible={this.state.visible}
                    onCancel={this.handleCancel}
                    footer={null}
                    >
            <Form
        layout
        name="basic"
        initialValues={{ remember: true }}
        onFinish={this.onFinish}
        onFinishFailed={this.onFinishFailed}
        >
        <Form.Item
            label="Event Name"
            name="eventName"
            rules={[{ required: true, message: 'Please input Event Name!' }]}
        >
            <Input />
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
            <InputNumber />
        </Form.Item>
        
        <Form.Item
            label="Event Question"
            name="eventQuestion"
            rules={[{ required: false }]}
        >
            <Input />
        </Form.Item>

        <Form.Item tailLayout>
            <Button type="primary" htmlType="submit">
            Submit
            </Button>
        </Form.Item>
        </Form>
        </Modal>
        <Button type="primary" onClick={this.showModal}>
        Add Event
      </Button></>
        );
        }
} 

export default ModalContent;