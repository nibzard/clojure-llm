(defn change_date_format
  "Write a function to convert a date of yyyy-mm-dd format to dd-mm-yyyy format."
  [dt]
  (when dt
    (clojure.string/join "-" (reverse (clojure.string/split dt #"-")))))