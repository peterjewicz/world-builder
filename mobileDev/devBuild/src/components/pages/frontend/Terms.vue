<template>
  <div>
    <Header showHomeFront="true"/>
    <div class="Terms">
      <h2>Worlds Crafter Terms Of Service</h2>
      <h3>Last Update May 27, 2020</h3>

      <p>
        Worlds Crafter is a web software operated by Total Web Connections LLC. Hereafter, any mention of Worlds Crafter is assumed to reference Total Web Connections LLC. By using the Worlds Crafter website or web software (the "service") you are agreeing to be bound by the following terms and conditions. If you disagree with any of the terms below please do not use Worlds Crafter. These terms govern the relationship between you, Total Web Connections LLC, and the use of the Worlds Crafter Software.
        We reserve the right to change, mofify or update the terms of service any anytime with or without notice. Your continued use of the service constitutes your acceptance of these changes. Please check back regularly as this web page will contain any such updats.
      </p>

      <h3>General Terms and Conditions</h3>
      <ul>
        <li>You must be 18 years or older to use the service</li>
        <li>You must provide valid and legally correct information when signing up for the service.</li>
        <li>You are responsible for the security of your account, including usernames and passwords. Total Web Connections is not liable for any losses or damages that may occur from failure to do so</li>
        <li>You may not use the service for any illegal purposes</li>
        <li>Your use of the service must not violate any laws in your jurisdiction</li>
        <li>While Total Web Connections has ensured best practices are followed in regards to data encryption, you understand that unencryped data transfers may occur and is out of our control</li>
        <li>We reserve the right to cancel the service for any account at any time for any reason. If your service is cancelled you are not entitled to a refrund</li>
        <li>You agree to not copy, duplicate, or sell any part of the service or website</li>
        <li>Total Web Connections may use third parties for certain tasks such as hosting, data storage, and marketing</li>
        <li>The service is provided "As-Is", and "As-Available". We do not guarantee the service will be 100% available or meet your specific requirements</li>
        <li>The failure to enforce any right or provision of these terms does not constitute a waiver of that right or provision.</li>
      </ul>

      <h3>Pricing</h3>
      <p>
        You must provide a valid credit card to use the service after the trail period. Total Web Connections may close your account at any time should a payment fail to post by its due date. Pricing for the service may change at any time. Any changes to pricing will be send to the email on file with the account, and such email will be send no less than (30) days before the new pricing structure is to take effect.
      </p>

      <h3>Account Cancellations and Refunds</h3>
      <p>
        You may cancell you account at any time by contacting us at info@worldscrafter.com or by navigating to the settings page in the service. Cancelling your account may cause a loss of ALL data stored with us and we are not responsible or any issues, damages, or otherwise that may arise from such loss.
        No refunds are given for cancellations.
      </p>

      <h3>Age requirements</h3>
      <p>
        The service is intended for use by adults and does not knowlingly collect information from children under 13 years of age. If you are the parent or legal guardian of a child under 13 who you believe has submitted personal information to us please contact us at info@worldscrafter.com.
        You must be atleast 18 years of age to subscribe to the service. Membership in the service is void where prohibited. By using the service, you represent and warrant that you have the right, authority, and capacity to enter into this agreement and agree to abide by all the terms and conditions of it.
      </p>

      <h3>Limitation of Liability</h3>
      <p>
        Except in jurisdictions where such provisions are restricted, in no event will Total Web Connections be liable to you or any third person for any direct, indirect, consequential, exemplary, incidental, special or punitive damages, including but not limited to, damages for loss of profits, goodwill, use, data or other intangible losses (even if Total Web Connections has been advised of the possibility of such damages), resulting from: (i) the use or the inability to use the Service; (ii) the cost of procurement of substitute goods and services resulting from your inability to access or obtain any goods, data, information or services through or from the Service; (iii) unauthorized access to or alteration of your transmissions or data; (iv) statements or conduct of any third party on the service; or (v) any content posted on the Website or transmitted to Registrants or Members; or (vi) any inaccurate or out-of-date content produced by the tools or published in the guides or blog, or Website; or (vii) any other matter relating to the Service. Nothwithstanding any provision to the contrary, Total Web Connections’s liability to you for any cause whatsoever, and regardless of the form of the action, will at all times be limited to the amount paid, if any, by you to Total Web Connections in the twelve (12) months prior to the claimed injury or damage.
      </p>

      <h3>Indemnity</h3>
      <p>
        You agree to indemnify and hold Total Web Connections, its subsidiaries, affiliates, officers, agents, and other partners and employees, harmless from any loss, liability, claim, or demand, including reasonable attorneys’ fees, made by any third party due to or arising out of your use of the Service in violation of this Agreement and/or arising from a breach of this Agreement and/or any breach of your representations and warranties and/or your negligent or willful acts, and/or the violation by you of Total Web Connections’s or any third party’s rights, including without limitation privacy rights, other property rights, trade secret, proprietary information, trademark, copyright, or patent rights, and claims for libel slander, or unfair trade practices in connection with the use or operation of the Service. You obligation to indemnify will survive the expiration or termination of this Agreement by either party for any reason.
      </p>

      <h3>Choice of Law</h3>
      <p>
        If any dispute arises out of use of the service, you expressly agree that any such dispute shall be governed by the laws of the State of Illinois. You also agree and consent to exclusive jurisdiction and venue of the state and federal courts of the State of Illnois, in Dupage County, for the resolution of such a dispute.
      </p>

      <h3>Questions?</h3>
      <p>
        If you have any questions or concerns with the above policies you can contact us at info@worldscrafter.com
      </p>

      <h3>Severability</h3>
      <p>
        In case any provision or term in this contract shall be held invalid, illegal or unenforceable, the validity, legality and enforceability of the remaining provisions shall not in any way be affected or impaired thereby.
      </p>
    </div>
  </div>
</template>

<script>
import Header from '../../global/HeaderFrontend.vue';

export default {
  name: 'Terms',
  components: {
    Header
  },
  data () {
    return {

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import '../../../styles/main';
  .Terms {
    max-width: 768px;
    margin: 0 auto;
    padding: 0 10px;
    margin-top: 150px;
    text-align: left;
  }
</style>
