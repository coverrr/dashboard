function loadEvents() {

	this.source = null;

	this.start = function() {

		var eventTable = document.getElementById("eventTable");

		this.source = new EventSource("/event/stream");

		this.source.addEventListener("message", function(event) {

			// These events are JSON, so parsing and DOM fiddling are needed
			var monitoringEvent = JSON.parse(event.data);

			var row = eventTable.getElementsByTagName("tbody")[0]
					.insertRow(0);
			var cell0 = row.insertCell(0);
			var cell1 = row.insertCell(1);
			var cell2 = row.insertCell(2);

			// cell0.className = "author-style";
			cell0.innerHTML = monitoringEvent.systemName;

			// cell1.className = "text";
			cell1.innerHTML = monitoringEvent.lastChecked;

			// cell2.className = "date";
			cell2.innerHTML = monitoringEvent.healthy;

		});

		this.source.onerror = function() {
			this.close();
		};

	};

	this.stop = function() {
		this.source.close();
	}

}

comment = new loadEvents();

window.onload = function() {
	comment.start();
};
window.onbeforeunload = function() {
	comment.stop();
}
