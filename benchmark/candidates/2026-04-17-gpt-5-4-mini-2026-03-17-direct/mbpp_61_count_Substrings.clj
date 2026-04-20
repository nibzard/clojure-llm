(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [digits (map #(Character/digit ^char % 10) (str s))
        n (count digits)]
    (loop [i 0
           total 0]
      (if (= i n)
        total
        (let [res (loop [j i
                         sum 0
                         cnt 0]
                    (if (= j n)
                      cnt
                      (let [sum' (+ sum (nth digits j))
                            cnt' (if (= sum' (inc (- j i))) (inc cnt) cnt)]
                        (recur (inc j) sum' cnt'))))]
          (recur (inc i) (+ total res)))))))