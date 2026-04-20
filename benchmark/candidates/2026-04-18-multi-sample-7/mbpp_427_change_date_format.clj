(defn change_date_format
  "	Write a function to convert a date of yyyy-mm-dd format to dd-mm-yyyy format."
  [dt]
  (when (some? dt)
    (let [[y m d] (clojure.string/split dt #"-" 3)]
      (when (and y m d)
        (str d "-" m "-" y)))))