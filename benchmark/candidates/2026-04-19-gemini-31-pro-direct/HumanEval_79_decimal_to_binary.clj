(defn decimal_to_binary
  [decimal]
  (str "db" (Long/toBinaryString decimal) "db"))