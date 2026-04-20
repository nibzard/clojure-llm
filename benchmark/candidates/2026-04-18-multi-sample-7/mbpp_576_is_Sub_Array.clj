(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (seq A)
        b (seq B)]
    (cond
      (nil? b) true
      (nil? a) false
      :else
      (let [n (count b)]
        (boolean
         (some #(= b %)
               (partition n 1 [] a)))))))