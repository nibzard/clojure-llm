(defn change_date_format
  "	Write a function to convert a date of yyyy-mm-dd format to dd-mm-yyyy format."
  [dt]
  (let [[y m d] (clojure.string/split dt #"-")]
    (str d "-" m "-" y)))