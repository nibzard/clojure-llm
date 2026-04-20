(defn decimal_to_binary
  [decimal]
  (str "db" (Integer/toBinaryString decimal) "db"))