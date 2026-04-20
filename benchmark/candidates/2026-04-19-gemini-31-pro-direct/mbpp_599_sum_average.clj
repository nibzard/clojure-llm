(defn sum_average [number]
  (if (or (nil? number) (<= number 0))
    [0 0]
    (let [sum (/ (* number (inc number)) 2)
          avg (/ sum number)]
      [sum avg])))