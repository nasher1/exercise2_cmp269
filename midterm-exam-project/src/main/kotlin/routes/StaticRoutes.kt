package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

fun Route.staticRoutes() {
    get("/") {
        call.respondText(
            contentType = io.ktor.http.ContentType.Text.Html,
            text = createIndexHtml()
        )
    }
}

private fun createIndexHtml(): String {
    return buildString {
        appendHTML().html {
            head {
                meta { charset = "UTF-8" }
                meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
                title { +"Student Portal" }
                style {
                    unsafe { +"""
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            min-height: 100vh;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            padding: 20px;
                        }
                        .container {
                            background: white;
                            border-radius: 20px;
                            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
                            padding: 40px;
                            max-width: 500px;
                            width: 100%;
                            text-align: center;
                        }
                        h1 { 
                            color: #333; 
                            margin-bottom: 10px;
                            font-size: 2.5em;
                        }
                        .subtitle {
                            color: #666;
                            margin-bottom: 30px;
                            font-size: 1.1em;
                        }
                        .input-group {
                            margin-bottom: 20px;
                            text-align: left;
                        }
                        label {
                            display: block;
                            margin-bottom: 8px;
                            color: #555;
                            font-weight: 500;
                        }
                        input {
                            width: 100%;
                            padding: 15px;
                            border: 2px solid #e1e5e9;
                            border-radius: 10px;
                            font-size: 16px;
                            transition: all 0.3s ease;
                        }
                        input:focus {
                            outline: none;
                            border-color: #667eea;
                            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
                        }
                        button {
                            width: 100%;
                            padding: 15px;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            color: white;
                            border: none;
                            border-radius: 10px;
                            font-size: 18px;
                            font-weight: 600;
                            cursor: pointer;
                            transition: all 0.3s ease;
                        }
                        button:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 10px 20px rgba(102, 126, 234, 0.3);
                        }
                        .info {
                            margin-top: 20px;
                            padding: 20px;
                            background: #f8f9fa;
                            border-radius: 10px;
                            text-align: left;
                        }
                        .qr-section {
                            margin-top: 30px;
                        }
                        .qr-section img {
                            max-width: 200px;
                            border-radius: 10px;
                            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
                        }
                        @media (max-width: 600px) {
                            .container { padding: 30px 20px; }
                            h1 { font-size: 2em; }
                        }
                    """ }
                }
            }
            body {
                div("container") {
                    h1 { +"🎓 Student Portal" }
                    p("subtitle") { +"Lehman College Student Information System" }
                    
                    div("input-group") {
                        label { +"Student ID" }
                        input(InputType.text) {
                            id = "studentId"
                            placeholder = "Enter your student ID (e.g., student1)"
                        }
                    }
                    
                    button(type = ButtonType.button) {
                        onClick = "fetchStudentInfo()"
                        +"Get Student Info & QR Code"
                    }
                    
                    div("info") { id = "studentInfo"; style = "display: none;" }
                    div("qr-section") { id = "qrSection"; style = "display: none;" }
                }
                
                script {
                    unsafe { +"""
                        async function fetchStudentInfo() {
                            const studentId = document.getElementById('studentId').value.trim();
                            if (!studentId) {
                                alert('Please enter a Student ID');
                                return;
                            }
                            
                            try {
                                const response = await fetch(`/api/student/${'$'}{studentId}`);
                                const student = await response.json();
                                
                                const infoDiv = document.getElementById('studentInfo');
                                const qrSection = document.getElementById('qrSection');
                                
                                infoDiv.innerHTML = `
                                    <h3>Student Profile</h3>
                                    <p><strong>Name:</strong> ${'$'}{student.name}</p>
                                    <p><strong>Major:</strong> ${'$'}{student.major || 'Undecided'}</p>
                                    <p><strong>Access Level:</strong> ${'$'}{student.accessLevel}</p>
                                `;
                                
                                infoDiv.style.display = 'block';
                                
                                // Generate QR code
                                const qrImg = document.createElement('img');
                                qrImg.src = `/generate-id?sid=${'$'}{studentId}`;
                                qrImg.alt = 'Student QR Code';
                                qrImg.loading = 'lazy';
                                
                                qrSection.innerHTML = '<h3>QR Code (Scan to Verify)</h3>';
                                qrSection.appendChild(qrImg);
                                qrSection.style.display = 'block';
                                
                            } catch (error) {
                                if (error.name === 'TypeError' && error.message.includes('404')) {
                                    alert('Student ID not found! Try: student1, student2, or student3');
                                } else {
                                    alert('Error fetching student data');
                                }
                            }
                        }
                        
                        // Allow Enter key to trigger search
                        document.getElementById('studentId').addEventListener('keypress', function(e) {
                            if (e.key === 'Enter') {
                                fetchStudentInfo();
                            }
                        });
                    """ }
                }
            }
        }
    }
}