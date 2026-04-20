(defn sum_in_range
  "	Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (long (min l r))
        end   (long (max l r))
        first-odd (if (odd? start) start (inc start))
        last-odd  (if (odd? end) end (dec end))
        n (when (<= first-odd last-odd)
            (inc (quot (- last-odd first-odd) 2)))]
    (if n
      (* n (+ first-odd last-odd) 1/2)
      0)))