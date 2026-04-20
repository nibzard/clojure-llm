(defn radian_degree
  "Write a function to convert degrees to radians."
  [degree]
  (when (some? degree)
    (* degree (/ Math/PI 180.0))))