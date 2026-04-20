(defn extract_values
  "	Write a function to extract values between quotation marks from a string."
  [text]
  (map second (re-seq #"\"([^\"]*)\"" text)))