<?php
    include 'config/db_config.php';

    $data = file_get_contents("php://input");
    
    $request = json_decode($data);
    
    $response = array();

    $isValidRequest = false;

    //{"action":"REGISTER_USER", "userName":"Mr. Ahmed"}
    
    if(isset($request->{'action'})){
    
        if($request->{'action'} == 'REGISTER_USER'){ 
            $isValidRequest = true;

            $userName = $request->{'userName'};
            $query = "INSERT INTO user('name') values('".$userName."')"; 
            $result = mysqli_query($connection, $query);
            if($result) {
                $response['userId'] = mysqli_insert_id($connection); 
                $response['status'] = true;
                $response['responseCode'] = 0; //success
                $response['message'] = "User registered successfully";
                }
                else{
                $response['status'] = false;
                $response['responseCode'] = 102; //User registration failed 
                $response['message']="User registration failed";
        }
        if($request->{'action'} == 'ADD_PROJECT'){ 
                $isValidRequest = true;

                $userId = $request->{'userId'};
                $title = $request->{'title'};
                $description = $request->{'description'};

                //add others

                $query = "INSERT INTO project('project_title', 'description', 'user_id') values('".$title."', '".$description."', '".$userId."')"; 
                $result = mysqli_query($connection, $query);

                if($result) {
                    $response['projectId'] = mysqli_insert_id($connection); 
                    $response['status'] = true;
                    $response['responseCode'] = 0; //success
                    $response['message']="Project added successfully";
                    }
                    else{
                    $response['status'] = false;
                    $response['responseCode'] 103;
                    $response['message']"Project addition failed";
            }

            if($request->{'action'} == 'GET_PROJECTS'){
                $isValidRequest = true;	
                $userId = $request -> {'userId'};
    
                $query = "SELECT p.id as projectId, u.id as userId, p.date_time as projectDateTime,u.date_time as userDateTime,p.*,u.* FROM project p INNER JOIN user u on p.user_id = u.id";
                $result = mysqli_query($connection,$query);
                if($result && mysqli_num_rows($result)>0){
                    $myBlogs = array();
                    $allBlogs = array();
                    while(($row = mysqli_fetch_assoc($result))!=null){
                        $blog = array();
                        $blog["projectId"] = $row['projectId'];
                        $blog["freelancerName"] = $row['name'];
                        $blog["title"] = $row['title'];
                        $blog["description"] = $row['description'];
                        $blog["dateTime"] = $row['projectDateTime'];
    
                        $allProject[] = $project;
    
                        if($row['userId'] == $userId){
                            $myProject[] =  $project;
                        }
                    }
    
                    $response['status'] = true; 
                    $response['responseCode'] = 0; //Project are available
                    $response['message'] ="Project are available";
                    $response['allProject'] =  $allProject;
                    $response['myProject']  = $myProject;
                }
                else{
                    $response['status'] = false; 
                    $response['responseCode'] = 104; //Project are not available
                    $response['message'] ="Project are not available";
                }
            }
    

        }
        if(!$isValidRequest){
        $response['status'] = false;
        $response['responseCode'] = 101; 
        $response['message']="Invalid Request Action";
        }
    } else{
        $response['status'] = false;
        $response['responseCode'] = 100; //Request action not defined 
        $response['message']="Request action not defined";
    }

    echo json_encode($response);
?>