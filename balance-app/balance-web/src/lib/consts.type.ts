export const BASE_API = "http://localhost:8080/balance/api/v1";
type BASE_API_PREFIX = typeof BASE_API;
export type ENDPOINT = `${BASE_API_PREFIX}/${string}`;

export const HEADER_JSON = {
    "Content-Type": "application/json"
}