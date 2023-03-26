function startTime() {
	const today = new Date();
	let currentMinutes = today.getMinutes();
	let currentSeconds = today.getSeconds();
	let mc = currentMinutes * 60 + currentSeconds;
	let h = parseInt(mc / 150);
	let m = parseInt(0.4 * (mc % 150));
	h = checkTime(h);
	m = checkTime(m);
	document.getElementById('txt').innerHTML = h + ":" + m;
	setTimeout(startTime, 2500);
}
function checkTime(i) {
	if (i < 10) { i = "0" + i };  // add zero in front of numbers < 10
	return i;
}