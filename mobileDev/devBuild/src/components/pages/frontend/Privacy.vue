<template>
  <div>
    <Header showHomeFront="true"/>
    <div class="Privacy">
      <h2>Worlds Crafter Privay Policy</h2>
      <p>
        Worlds Crafter takes your privacy and the policy of your information seriously. This Privacy Policy describes what information gets collected, how the information may be used, and with whom. The Wolds Crater Application is owned and operated by Total Web Connections LLC.
      </p>

      <h3>Tracking</h3>
      <p>
        The Worlds Crafter service uses cookies to help track users, enable certain functionality, and gauge customer activiy.
      </p>

      <h3>
        Information Disclosure
      </h3>
      <p>
        The Worlds Crafter service does not sell or rent any personal information to to any marketers or thrid parties. Worlds Crafter may disclose personally identifiable information under special circumstances, such as to comply with subpoenas, warrants, other valid legal processes, or when your actions violate the Terms of Service. In addition, Worlds Crafter may disclose certain information to our service providers, such as credit-card payment processors, performing services on our behalf. These service providers may have access to personal information needed to perform their functions.
      </p>

      <h3>
        Children’s Online Privacy Protection Act
      </h3>

      <p>
        The Worlds Crafter does not knowingly collect or solicit personal information from anyone under the age of 13. We also do not knowlingly allow those under 13 to register or use the service.
      </p>

      <h3>
        Who has access to my data?
      </h3>
      <p>
        The Worlds Crafter service uses third party vendors and hosting partners to provide the hardware, software, storage, networking, and technology to run the Service and retains all rights to the application.
      </p>

      <h3>
        Billing
      </h3>
      <p>
        The Worlds Crafter service does not keep complete billing information on it's servers or anywhere in the appliction. Payments made via credit card are handled by Stripe.com, a PCI compliant payment processor. Stripe handles the process of storing and billing users of the service.
      </p>

      <h3>
        Questions?
      </h3>
      <p>
        If you have any questions or concerns with the above policies you can contact us at info@worldscrafter.com
      </p>
    </div>
  </div>
</template>

<script>
import Header from '../../global/HeaderFrontend.vue';

export default {
  components: {
    Header
  },
  name: 'Privacy',
  data () {
    return {

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import '../../../styles/main';
  .Privacy {
    max-width: 768px;
    margin: 0 auto;
    padding: 0 10px;
    text-align: left;
    margin-top: 150px;
  }
</style>
