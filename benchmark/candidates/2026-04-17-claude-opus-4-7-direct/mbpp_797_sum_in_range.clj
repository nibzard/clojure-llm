(defn sum_in_range
  "Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (if (odd? l) l (inc l))
        end (if (odd? r) r (dec r))]
    (if (> start end)
      0
      (let [cnt (inc (quot (- end start) 2))]
        (* cnt (+ start end))))))