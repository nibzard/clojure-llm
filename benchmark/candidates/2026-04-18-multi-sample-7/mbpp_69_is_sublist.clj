(defn is_sublist
  "	Write a function to check whether a list contains the given sublist or not."
  [l s]
  (let [l (seq l)
        s (seq s)]
    (cond
      (nil? s) true
      (nil? l) false
      :else
      (let [n (count s)]
        (boolean
          (some #(= s %)
                (partition n 1 [] l)))))))