(defn left_insertion
  "	Write a function to locate the left insertion point for a specified value in sorted order. https://www.w3resource.com/cljthon-exercises/data-structures-and-algorithms/cljthon-data-structure-exercise-24.php"
  [a x]
  (let [v (vec (or a []))
        n (count v)]
    (loop [lo 0
           hi n]
      (if (< lo hi)
        (let [mid (quot (+ lo hi) 2)]
          (if (< (v mid) x)
            (recur (inc mid) hi)
            (recur lo mid)))
        lo))))