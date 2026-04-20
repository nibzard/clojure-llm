(defn closest_integer
  [value]
  (let [n (Double/parseDouble value)
        sign (if (neg? n) -1 1)
        abs-n (Math/abs n)
        ;; Add 0.5 and floor to round away from zero for positive numbers
        ;; (e.g., 14.5 -> 15.0 -> 15, 14.4 -> 14.9 -> 14)
        rounded-abs (Math/floor (+ abs-n 0.5))]
    (* sign (int rounded-abs))))