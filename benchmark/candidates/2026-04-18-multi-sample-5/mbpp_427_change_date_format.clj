(defn change_date_format
  "	Write a function to convert a date of yyyy-mm-dd format to dd-mm-yyyy format."
  [dt]
  (when (some? dt)
    (let [parts (clojure.string/split dt #"-")]
      (when (= 3 (count parts))
        (clojure.string/join "-" (reverse parts))))))