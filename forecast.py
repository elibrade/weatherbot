import fmi_weather_client as fmi
from fmi_weather_client.errors import ClientError, ServerError
import sys

def forecast(location):
    try:
        forecast = fmi.forecast_by_place_name(location)
        message = ""
        for weather_data in forecast.forecasts:
            time    = f"{weather_data.time}"
            temperature = f"{weather_data.temperature}"
            message = " ".join([message, time[:10] + ": " + temperature[:-3] + " C"])
        return message
    except ClientError as err:
        # Catch and return client errors (invalid coordinate, unknown place, etc.)
        return f"FMI returned a client error {err.status_code}: {err.message}"

    except ServerError as err:
        # Catch and return server errors
        return f"FMI returned a server error {err.status_code}: {err.body}"


if __name__ == '__main__':
    location    = sys.argv[1]
    result      = forecast(location)
    print(result)
