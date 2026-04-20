(defn change_date_format
  "Write a function to convert a date of yyyy-mm-dd format to dd-mm-yyyy format."
  [dt]
  (when (seq dt)
    (let [[yyyy mm dd] (clojure.string/split dt #"-")]
      (when (and yyyy mm dd)
        (str dd "-" mm "-" yyyy)))))