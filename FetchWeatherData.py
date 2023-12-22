import fmi_weather_client as fmi
from fmi_weather_client.errors import ClientError, ServerError
import sys

def get_weather_report(location):
    try:
        forecast = fmi.forecast_by_place_name(location)
        return f"{forecast.place}: {forecast.forecasts[2].temperature.value} C"

    except ClientError as err:
        # Catch and return client errors (invalid coordinate, unknown place, etc.)
        return f"FMI returned a client error {err.status_code}: {err.message}"

    except ServerError as err:
        # Catch and return server errors
        return f"FMI returned a server error {err.status_code}: {err.body}"

if __name__ == '__main__':
    location = sys.argv[1]
    get_weather_report(location)
