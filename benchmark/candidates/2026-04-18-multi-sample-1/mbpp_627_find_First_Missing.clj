(defn find_First_Missing
  "	Write a cljthon function to find the smallest missing number from a sorted list of natural numbers."
  [array]
  (let [v (vec (or array []))
        n (count v)]
    (loop [i 0]
      (cond
        (= i n) n
        (not= (nth v i) i) i
        :else (recur (inc i))))))