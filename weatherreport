import fmi_weather_client as fmi
from fmi_weather_client.errors import ClientError, ServerError
import sys

def weather_report(location):
    try:
        weather = fmi.weather_by_place_name(location)
        message = f"{weather.place}: {weather.data.temperature}"
        return message[:-3] + " C"
    except ClientError as err:
        # Catch and return client errors (invalid coordinate, unknown place, etc.)
        return f"FMI returned a client error {err.status_code}: {err.message}"

    except ServerError as err:
        # Catch and return server errors
        return f"FMI returned a server error {err.status_code}: {err.body}"


if __name__ == '__main__':
    location    = sys.argv[1]
    result      = weather_report(location)
    result
