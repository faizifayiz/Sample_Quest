<?php

include("connection.php");

$name = $_POST['name'];
$job = $_POST['job'];
$address = $_POST['address'];
$company = $_POST['company'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$employee_meet = $_POST['employee_meet'];
$employee_to_meet_label = $_POST['employee_to_meet_label'];
$identification = $_POST['identification'];
$identification_label = $_POST['identification_label'];
$purpose_of_visit = $_POST['selectedPurpose'];


$sql ="INSERT INTO constructor (name,job,address,company,email,phone,employee_meet,employee_to_meet_label,identification,identification_label,purpose_of_visit) 
VALUES ('$name','$job','$address','$company','$company','$phone','$employee_meet','$employee_to_meet_label','$identification','$identification_label','$purpose_of_visit')";



if(mysqli_query($con,$sql)){
	
	echo "success";
}
else{
	
	echo "failed";
}


?>