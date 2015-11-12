<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Person Table</title>
    <style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {
        padding: 15px;
    }
</style>
</head>
<body>
<h2>Person: </h2>

<table style="width:100%">
    <tr>
        <td>ID</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Email</td>
        <td>Description</td>
        <td>Street</td>
        <td>City</td>
        <td>State</td>
        <td>Zip</td>
        <td>Organization Id</td>
        <td>Organization Name</td>
    </tr>
    <tr>
        <td>
            ${person.id}
        </td>
        <td>
            ${person.firstname}
        </td>
        <td>
            ${person.lastname}
        </td>
        <td>
            ${person.email}
        </td>
        <td>
            ${person.description}
        </td>
        <td>
            ${person.address.street}
        </td>
        <td>
            ${person.address.city}
        </td>
        <td>
            ${person.address.state}
        </td>
        <td>
            ${person.address.zip}
        </td>
        <td>
            ${person.org.id}
        </td>
        <td>
            ${person.org.name}
        </td>
    </tr>
</table>
</body>
</html>