(defn extract_values
  "	Write a function to extract values between quotation marks from a string."
  [text]
  (if (string? text)
    (mapv second (re-seq #"\"([^\"]*)\"" text))
    []))