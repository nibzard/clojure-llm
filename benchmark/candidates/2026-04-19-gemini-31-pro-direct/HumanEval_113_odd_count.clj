(defn odd_count [lst]
  (mapv (fn [s]
          (let [odd-count (count (filter #{\1 \3 \5 \7 \9} s))]
            (clojure.string/replace "the number of odd elements in the string i of the input."
                                    "i"
                                    (str odd-count))))
        lst))