package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.DateUtils;
import utils.Generated;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Flight {
    private final int id;
    private final int startAirportId;
    private final int arrivalAirportId;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private final int planeId;
    private final int[] pilotIds;

    @Generated
    public int getId() {
        return id;
    }

    @Generated
    public int getStartAirportId() {
        return startAirportId;
    }

    @Generated
    public int getArrivalAirportId() {
        return arrivalAirportId;
    }

    @Generated
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Generated
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Generated
    public int getPlaneId() {
        return planeId;
    }

    @Generated
    public int[] getPilotIds() {
        return pilotIds;
    }

    @Generated
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isNow(Clock clock) {
        LocalDateTime now = LocalDateTime.now(clock);
        return startTime.isBefore(now) && endTime.isAfter(now);
    }

    public Flight(int id, int startAirportId, int arrivalAirportId, LocalDateTime startTime, LocalDateTime endTime, int planId, int[] pilotIds) {
        this.id = id;
        this.startAirportId = startAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planeId = planId;
        this.pilotIds = pilotIds;
    }

    public Flight(InputStream inputStream, PrintStream printStream) {
        try {
            Scanner in = new Scanner(inputStream);
            printStream.println("Enter id: ");
            id = in.nextInt();
            printStream.println("Enter start airport id: ");
            startAirportId = in.nextInt();
            printStream.println("Enter arrival airport id: ");
            arrivalAirportId = in.nextInt();
            printStream.println("Enter start time(dd/mm/yyyy hh:mm format): ");
            in.nextLine();
            startTime = DateUtils.parseTime(in.nextLine());
            printStream.println("Enter end time(dd/mm/yyyy hh:mm format): ");
            endTime = DateUtils.parseTime(in.nextLine());
            printStream.println("Enter plane id: ");
            planeId = in.nextInt();
            printStream.println("Enter pilot ids(separated by space): ");
            pilotIds = Arrays.stream(in.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            printStream.println("Invalid data");
            throw e;
        }
    }

    public static Flight fromJson(JSONObject json) {
        String id = json.get("id").toString();
        String startAirportId = json.get("startAirportId").toString();
        String arrivalAirportId = json.get("arrivalAirportId").toString();
        String startTime = json.get("startTime").toString();
        String endTime = json.get("endTime").toString();
        String planeId = json.get("planeId").toString();
        JSONArray pilotIdsJson = (JSONArray) json.get("pilotIds");
        int[] pilotIds = new int[pilotIdsJson.size()];
        for (int i = 0; i < pilotIdsJson.size(); i++) {
            pilotIds[i] = Integer.parseInt(pilotIdsJson.get(i).toString());
        }

        return new Flight(
                Integer.parseInt(id),
                Integer.parseInt(startAirportId),
                Integer.parseInt(arrivalAirportId),
                DateUtils.parseTime(startTime),
                DateUtils.parseTime(endTime),
                Integer.parseInt(planeId),
                pilotIds
        );
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("startAirportId", startAirportId);
        json.put("arrivalAirportId", arrivalAirportId);
        json.put("startTime", DateUtils.formatTime(startTime));
        json.put("endTime", DateUtils.formatTime(endTime));
        json.put("planeId", planeId);
        JSONArray pilotsIdsJson = new JSONArray();
        for (int id : pilotIds) {
            pilotsIdsJson.add(id);
        }
        json.put("pilotIds", pilotsIdsJson);
        return json;
    }

    @Generated
    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", startAirportId=" + startAirportId +
                ", arrivalAirportId=" + arrivalAirportId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", planId=" + planeId +
                ", pilotIds=" + Arrays.toString(pilotIds) +
                '}';
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && startAirportId == flight.startAirportId && arrivalAirportId == flight.arrivalAirportId && planeId == flight.planeId && Objects.equals(startTime, flight.startTime) && Objects.equals(endTime, flight.endTime) && Arrays.equals(pilotIds, flight.pilotIds);
    }

    @Generated
    @Override
    public int hashCode() {
        int result = Objects.hash(id, startAirportId, arrivalAirportId, startTime, endTime, planeId);
        result = 31 * result + Arrays.hashCode(pilotIds);
        return result;
    }
}
