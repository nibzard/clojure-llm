(defn sum_in_range
  "	Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (min l r)
        end (max l r)
        first-odd (if (odd? start) start (inc start))
        last-odd (if (odd? end) end (dec end))]
    (if (or (nil? l)
            (nil? r)
            (> first-odd last-odd)
            (neg? last-odd))
      0
      (let [first-nat-odd (max 1 first-odd)
            count-odds (inc (quot (- last-odd first-nat-odd) 2))]
        (if (pos? count-odds)
          (/ (* count-odds (+ first-nat-odd last-odd)) 2)
          0)))))