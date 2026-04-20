(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (map (fn [z]
         (let [r (Math/sqrt (+ (* (real-part z) (real-part z))
                               (* (imag-part z) (imag-part z))))
               theta (Math/atan2 (imag-part z) (real-part z))]
           [r theta]))
       numbers))