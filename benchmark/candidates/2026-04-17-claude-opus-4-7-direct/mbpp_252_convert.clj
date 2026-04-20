(defn convert
  "Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (if (number? numbers)
    (let [r (Math/sqrt (+ (* (real-part numbers) (real-part numbers))
                          (* (imag-part numbers) (imag-part numbers))))
          theta (Math/atan2 (imag-part numbers) (real-part numbers))]
      [r theta])
    (map convert numbers)))