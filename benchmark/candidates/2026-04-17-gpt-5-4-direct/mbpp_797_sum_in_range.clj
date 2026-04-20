(defn sum_in_range
  "	Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (max 1 (min l r))
        end   (max l r)
        first-odd (if (odd? start) start (inc start))
        last-odd  (if (odd? end) end (dec end))]
    (if (> first-odd last-odd)
      0
      (let [n (inc (quot (- last-odd first-odd) 2))]
        (/ (* n (+ first-odd last-odd)) 2)))))