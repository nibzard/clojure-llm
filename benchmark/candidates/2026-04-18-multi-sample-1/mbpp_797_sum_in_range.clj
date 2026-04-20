(defn sum_in-range
  "	Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (max 1 (min l r))
        end   (max l r)
        first-odd (if (odd? start) start (inc start))]
    (if (> first-odd end)
      0
      (let [n (inc (quot (- end first-odd) 2))]
        (* n (+ first-odd (dec (+ first-odd (* 2 n)))) 1/2)))))