 <?php
include("connection.php");


$sql ="SELECT * FROM purpose_of_visit ";

//echo $sql;
	
	
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result) > 0){

	while($row = mysqli_fetch_assoc($result))
		$data["data"][] = $row;
	echo json_encode($data);
}
else{
	echo "failed";
}
?>