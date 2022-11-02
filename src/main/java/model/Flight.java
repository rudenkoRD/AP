package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Flight {
    private final int id;
    private final int startAirportId;
    private final int arrivalAirportId;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private final int planeId;
    private final int[] pilotIds;

    public int getId() {
        return id;
    }

    public int getStartAirportId() {
        return startAirportId;
    }

    public int getArrivalAirportId() {
        return arrivalAirportId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getPlaneId() {
        return planeId;
    }

    public int[] getPilotIds() {
        return pilotIds;
    }

    public boolean isNow() {
        LocalDateTime now = LocalDateTime.now();
        return startTime.isBefore(now) && endTime.isAfter(now);
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    public Flight() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter id: ");
            id = in.nextInt();
            System.out.println("Enter start airport id: ");
            startAirportId = in.nextInt();
            System.out.println("Enter arrival airport id: ");
            arrivalAirportId = in.nextInt();
            System.out.println("Enter start time(dd/mm/yyyy hh:mm format): ");
            in.nextLine();
            startTime = DateUtils.parseTime(in.nextLine());
            System.out.println("Enter end time(dd/mm/yyyy hh:mm format): ");
            endTime = DateUtils.parseTime(in.nextLine());
            System.out.println("Enter plane id: ");
            planeId = in.nextInt();
            System.out.println("Enter pilot ids(separated by space): ");
            pilotIds = Arrays.stream(in.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("Invalid data");
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
}
