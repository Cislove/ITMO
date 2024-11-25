import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.6/firebase-app.js";
import { getDatabase, ref, push, onValue } from "https://www.gstatic.com/firebasejs/9.6.6/firebase-database.js";

const firebaseConfig = {
  apiKey: "AIzaSyCU9jqVlocaFw-sEUAwHQ9xLEx8Ki3I19c",
  authDomain: "test-phorum.firebaseapp.com",
  databaseURL: "https://test-phorum-default-rtdb.firebaseio.com",
  projectId: "test-phorum",
  storageBucket: "test-phorum.appspot.com",
  messagingSenderId: "128372504079",
  appId: "1:128372504079:web:6a9dc174c90743d92d157a",
  measurementId: "G-2J1YE48YKR"
};

const app = initializeApp(firebaseConfig);
const db = getDatabase(app);

function sendMessage() {
  const messageInput = document.getElementById("messageInput");
  const messageText = messageInput.value;
  if (messageText.trim() !== "") {
    const messagesRef = ref(db, 'messages');
    push(messagesRef, {
      text: messageText,
      timestamp: Date.now()
    });
    messageInput.value = ""; 
  }
}

document.getElementById("sendButton").addEventListener("click", sendMessage);

const messagesRef = ref(db, 'messages');
onValue(messagesRef, (snapshot) => {
  const messagesDiv = document.getElementById("messages");
  messagesDiv.innerHTML = ""; 
  snapshot.forEach((childSnapshot) => {
    const messageData = childSnapshot.val();
    const messageElem = document.createElement("div");
    messageElem.innerText = messageData.text;
    messagesDiv.appendChild(messageElem);
  });
});
