(defn extract_quotation
  "	Write a function to extract values between quotation marks \" \" of the given string."
  [text1]
  (when (some? text1)
    (mapv second (re-seq #"\"([^\"]*)\"" text1))))