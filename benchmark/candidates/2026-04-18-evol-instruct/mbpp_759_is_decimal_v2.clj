(defn count-decimals
  "Return the number of decimal values in a collection that have exactly 2 digits after the decimal point.
  
  Values may be numbers or strings. Ignore nil values.
  
  Examples:
  (count-decimals [1.23 4.5 6.789 \"8.90\" nil]) ;=> 2
  (count-decimals [\"12\" \"3.00\" 7 9.1])       ;=> 1"
  [xs]
  (let [pattern #"^-?\d+\.\d{2}$"]
    (count (filter
            (fn [v]
              (when (some? v)
                (let [s (cond
                          (string? v) v
                          (number? v) (str v)
                          :else (str v))]
                  (re-matches pattern s))))
            xs))))